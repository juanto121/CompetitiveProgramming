// @EXPECTED_RESULTS@: CORRECT

//////////////////
// Piet-Hein.cc //
//////////////////

#include <iostream>
#include <vector>
#include <set>
#include <cassert>

using namespace std;

typedef set<unsigned> uset;

inline bool contains(const uset & S, const unsigned & value)
{
    return S.find(value) != S.end();
}

void visit_cliques(const vector<unsigned> & values, const vector<uset> & chains, uset & chaingroup, unsigned value, unsigned & max_value)
{
    unsigned current = *chaingroup.rbegin(); // current vessel (last one added to the chain-group)

    value += values[current]; // add value of node I am visiting NOW.

    max_value = max(value, max_value);

    // walk all candidates for the next vessel in the chain-group

    const uset & outgoing = chains[current];

    for (uset::const_iterator candidate_iterator = outgoing.begin(); candidate_iterator != outgoing.end(); ++candidate_iterator)
    {
        const unsigned & candidate(*candidate_iterator);

        // verify that all members of the chaingroup so far link to the candidate.
        bool ok = true;

        for (uset::const_iterator member_iterator = chaingroup.begin(); member_iterator != chaingroup.end(); ++member_iterator)
        {
            const unsigned & member(*member_iterator);

            if (!contains(chains[member], candidate))
            {
                ok = false;
                break;
            }
        }

        if (ok)
        {
            // it can be part of the chain-group. Add it and recurse.
            uset::const_iterator remove_me_afterwards = chaingroup.insert(candidate).first;
            visit_cliques(values, chains, chaingroup, value, max_value);
            chaingroup.erase(remove_me_afterwards);
        }
    }
}

int main()
{
    //unsigned numTrials;
    //cin >> numTrials; // ignore; TODO: remove, eventually

    for (unsigned nv, ne; cin >> nv >> ne;)
    {
        vector<unsigned> values;
        values.reserve(nv);

        for (unsigned i = 0; i < nv; ++i)
        {
            //unsigned id, value;
            //cin >> id >> value;
            //assert(id == i + 1);
            unsigned value;
            cin >> value;
            values.push_back(value);
        }

        vector<uset> chains(nv);

        for (unsigned i = 0; i < ne; ++i)
        {
            unsigned v1, v2;
            cin >> v1 >> v2;
            assert(1 <= v1 && v1 <= nv);
            assert(1 <= v2 && v2 <= nv);
            --v1;
            --v2;

            assert(v1 < v2);
            assert(!contains(chains[v1], v2));

            chains[v1].insert(v2);
        }

        unsigned max_value = 0;

        for (unsigned start = 0; start < nv; ++start)
        {
            uset visited;
            visited.insert(start);
            visit_cliques(values, chains, visited, 0, max_value);
        }

        cout << max_value << endl;
    }

    return 0;
}
