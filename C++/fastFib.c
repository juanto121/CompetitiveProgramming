# include<stdio.h>
int fib(int term, int val = 1, int prev = 0){
	if (term == 0) return prev;
	if (term == 1) return val;
	return fib(term-1, val+prev, val);
}
int int main(int argc, char const *argv[])
{
	fib(10);
	return 0;
}