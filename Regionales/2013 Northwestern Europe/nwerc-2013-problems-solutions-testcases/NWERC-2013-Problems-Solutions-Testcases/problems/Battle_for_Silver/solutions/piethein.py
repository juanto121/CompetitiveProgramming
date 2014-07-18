import sys
import Queue

file = open('C:/Users/b.pat-el/Documents/T.NET/CHipCie/NWERC-2013/problems/C06_PietHein/testcases/generated_30000.in', 'r')

CurrentCase = 0

class Vessel:
	# id: int
	# loot: int
	def __init__(self, id, loot):
		self.id = id
		self.loot = loot
		self.chains = []

	# return: int
	def getDegree(self):
		return len(self.chains)

	def getNeighbours(self):
		result = []
		for chain in self.chains:
			if chain.start != self:
				result.append(chain.start)
			else: result.append(chain.end)
		return result

	def __str__(self):
		return str(self.id) + "(" + str(self.loot) + ")"

	def __repr__(self):
		return self.__str__()

class Chain:
	# s, e: Vessel
	def __init__(self, s, e):
		self.start = s
		self.end = e

	def __str__(self):
		return str(self.start.id) + " -> " + str(self.end.id)

	def __repr__(self):
		return self.__str__()

# Vessels: List<Vessel>
class Chaingroup:
	def __init__(self, vessels):
		self.vessels = vessels
	
	# return: int
	def getSize(self):
		return len(self.vessels)

	# return: int
	def getLoot(self):
		result = 0
		for vessel in self.vessels:
			result += vessel.loot

		return result

	def __str__(self):
		result = ""
		result += "Vessels:\n"
		for vessel in self.vessels:
			result += "\t" + str(vessel) + "\n"
		result += "Total loot: " + str(self.getLoot()) + "\n"
		return result

	def __repr__(self):
		return self.__str__()


class Squadron:
	def __init__(self):
		self.vessels = set([])
		self.chains = set([])

	# id: int
	# return: Vessel
	def getVesselWithId(self, id):
		for vessel in self.vessels:
			if vessel.id == id:
				return vessel
		return None

	def removeVessel(self, id):
		for vessel in self.vessels:
			if vessel.id == id:
				self.removeVessel(vessel)
				break

	def removeVessel(self, vessel):
		#print "Removing vessel: " + str(vessel.id)
		self.vessels.remove(vessel)

		# Remove all chains bound to this vessel
		for chain in vessel.chains:
			self.chains.remove(chain)
			for buur in vessel.getNeighbours():
				if chain in buur.chains: 
					buur.chains.remove(chain)
					#print "Chain removed: " + str(chain)

	def removeVertexesWithDegreeLowerThan(self, degree):
		for vessel in self.vessels[:]:
			if vessel.getDegree() < degree:
				self.removeVessel(vessel)

	def __str__(self):
		result = ""
		result += "Vessels:\n"
		for vessel in self.vessels:
			result += "\t" + str(vessel) + "\n"
		result += "Chains:\n"
		for chain in self.chains:
			result += "\t" + str(chain) + "\n"
		return result

	def __repr__(self):
		return self.__str__()

def doFloodfillForInputCheck(graph, verbose = False):
	toSearch = set()
	visited = set()

	toSearch.add(1)
	while len(toSearch) > 0:
		v = toSearch.pop()
		if verbose: print v

		visited.add(v)
		vessel = graph.getVesselWithId(v)

		for vessel_chain in vessel.chains:
			start = vessel_chain.start.id
			end = vessel_chain.end.id
			if (start != v and start not in visited):
				if start not in toSearch:
					toSearch.add(start)
			if (end != v and end not in visited):
				if end not in toSearch:
					toSearch.add(end)

	return len(visited)

def findCliquesOfSize2(graph, verbose = False):
	# Each edge denotes a clique of size 2
	result = []
	for chain in graph.chains:
		group = Chaingroup([ chain.start, chain.end ])
		result.append(group)
	
	return result

def findCliquesOfSize3(graph, verbose = False):
	# Remove vertexes with a degree smaller than 2, as they cannot be part of a clique of size 3
	graph.removeVertexesWithDegreeLowerThan(2)

	# For each vertex
	result = dict()
	alreadyHandled = dict()
	for vertex in graph.vessels:
		alreadyHandled[vertex] = 0

	n = 0
	for vertex in graph.vessels[:]:
		n += 1
		if verbose: print vertex
		# Seek the neighbours of that vertex
		for buur in vertex.getNeighbours()[:]:
			n += 1
			if buur == vertex or alreadyHandled[buur] == 1: continue
			if verbose: print "\t" + str(buur)
			# Seek the neighbours of the neighbour
			for buur2 in buur.getNeighbours()[:]:
				n += 1
				if buur2 == vertex or alreadyHandled[buur2] == 1: continue
				if verbose: print "\t\t" + str(buur2)
				# Seek the neighbours of the neighbour
				for buur3 in buur2.getNeighbours()[:]:
					if alreadyHandled[buur3] == 1: continue
					n += 1
					# If that neighbour is connected to the original vertex, we have a clique of size 3
					if buur3 == vertex:
						# We found a clique of size 3!
						foundGroup = Chaingroup([ vertex, buur, buur2 ])
						loot = foundGroup.getLoot()
						result[loot] = foundGroup
						if verbose: print "\t\t\t" + str(buur3) + "!"
					elif verbose: print "\t\t\t" + str(buur3) + " :|"
		# Remove the vertex
		alreadyHandled[vertex] = 1

	if verbose: print "Total steps: " + str(n)

	return result.values()

def findCliquesOfSize4(graph, verbose = False):
	# Remove vertexes with a degree smaller than 3, as they cannot be part of a clique of size 4
	graph.removeVertexesWithDegreeLowerThan(3)

	# For each vertex
	result = dict()
	alreadyHandled = dict()
	for vertex in graph.vessels:
		alreadyHandled[vertex] = 0

	n = 0
	for vertex in graph.vessels[:]:
		n += 1
		if verbose: print vertex
		# Seek the neighbours of that vertex
		for buur in vertex.getNeighbours()[:]:
			n += 1
			if alreadyHandled[buur] == 1: continue
			if verbose: print "\t" + str(buur)
			# Seek the neighbours of the neighbour
			for buur2 in buur.getNeighbours()[:]:
				n += 1
				if alreadyHandled[buur2] == 1 or buur2 == vertex: continue
				n += len(buur2.getNeighbours())
				if vertex not in buur2.getNeighbours(): continue
				if verbose: print "\t\t" + str(buur2)
				# Seek the neighbours of the neighbour
				for buur3 in buur2.getNeighbours()[:]:
					n += 1
					if alreadyHandled[buur3] == 1 or buur3 == buur or buur3 == vertex: continue
					buren = buur3.getNeighbours()
					n += len(buren)
					if vertex in buren and buur in buren:
						# We found a clique of size 4!
						foundGroup = Chaingroup([ vertex, buur, buur2, buur3 ])
						loot = foundGroup.getLoot()
						result[loot] = foundGroup
						if verbose: print "\t\t\t" + str(buur3) + "!"
						break
					elif verbose: print "\t\t\t" + str(buur3) + " :/"

		# Remove the vertex
		alreadyHandled[vertex] = 1

	if verbose: print "Total steps: " + str(n)
	return result.values()

# graph: Squadron
def findCliques(graph, verbose = False):
	cliquesSize2 = findCliquesOfSize2(graph, verbose = verbose)
	if verbose:
		print "Cliques of size 2:"
		print cliquesSize2

	cliquesSize3 = findCliquesOfSize3(graph, verbose = verbose)
	if verbose:
		print "Cliques of size 3:"
		print cliquesSize3

	cliquesSize4 = findCliquesOfSize4(graph, verbose = verbose)
	if verbose:
		print "Cliques of size 4:"
		print cliquesSize4

	return cliquesSize2 + cliquesSize3 + cliquesSize4

def solve(verbose = False):
	global CurrentCase
	CurrentCase += 1
	if verbose: print "Case: " + str(CurrentCase)

	input = file.readline()
	inputs = input.split(' ')
	try:
		v = int(inputs[0])
	except ValueError:
		return False

	if v < 2 or v > 450:
		raise Exception("Input error in case " + str(CurrentCase) + ": number of vessels is out of range: " + str(v))
	c = int(inputs[1])
	if c < 1 or c > 900:
		raise Exception("Input error in case " + str(CurrentCase) + ": number of chains is out of range: " + str(c))

	vessels = []
	for i in range(0, v):
		input = file.readline()
		inputs = input.split(' ')
		if int(inputs[0]) < 100 or int(inputs[0]) > 6000:
			raise Exception("Input error in case " + str(CurrentCase) + ": amount of silver coins is out of range: " + str(int(inputs[0])))

		vessels.append(Vessel(i + 1, int(inputs[0])))

	chains = []
	for i in range(0, c):
		input = file.readline()
		inputs = input.split(' ')
		if int(inputs[0]) < 1 or int(inputs[0]) > v:
			raise Exception("Input error in case " + str(CurrentCase) + ": ID is out of range, while reading start point of chain: " + str(int(inputs[0])))
		if int(inputs[1]) < 1 or int(inputs[1]) > v:
			raise Exception("Input error in case " + str(CurrentCase) + ": ID is out of range, while reading end point of chain: " + str(int(inputs[1])))

		startVessel = None
		endVessel = None
		for _v in vessels:
			if _v.id == int(inputs[0]):
				startVessel = _v
				break
		for _v in vessels:
			if _v.id == int(inputs[1]):
				endVessel = _v
				break
		if startVessel.id == endVessel.id:
			raise Exception("Input error in case " + str(CurrentCase) + ": c_s == c_e: " + str(inputs[0]) + ", " + str(inputs[1]))
		if startVessel.id > endVessel.id:
			raise Exception("Input error in case " + str(CurrentCase) + ": c_s > c_e: " + str(inputs[0]) + ", " + str(inputs[1]) + "; " + str(startVessel) + ", " + str(endVessel))

		newChain = Chain(startVessel, endVessel)
		if newChain in chains:
			raise Exception("Input error in case " + str(CurrentCase) + ": chain already exists in input: " + str(inputs[0]) + ", " + str(inputs[1]))

		chains.append(newChain)
		startVessel.chains.append(newChain)
		endVessel.chains.append(newChain)

	graph = Squadron()
	graph.chains = chains
	graph.vessels = vessels

	# Do connectivity check
	if doFloodfillForInputCheck(graph, verbose = verbose) < v:
		raise Exception("Input error in case " + str(CurrentCase) + ": Graph was not connected!")

	if verbose: print graph

	cliques = findCliques(graph, verbose = verbose)

	if verbose: print cliques
	# Find largest clique
	largest = 0
	for group in cliques:
		if group.getLoot() > largest:
			largest = group.getLoot()
	print largest

	return True

#outfile = open("all.out", 'w')
#sys.stdout = outfile
try:
	verbose = False
	while solve(verbose = verbose): pass
	file.close()
	sys.stdout = sys.__stdout__
	print "Done!"
except EOFError:
	file.close()
	sys.stdout = sys.__stdout__
	print "Done!"
