
// @EXPECTED_RESULTS@: CORRECT

#include <stdio.h>

int gcd(int a,int b)
{
    return a ? gcd(b % a, a) : b;
}

int main()
{
    int a,b,c;
    while (scanf("%d%d%d",&a,&b,&c) == 3)
    {
        a *= b;
        c -= b;
        b = gcd(a, c);
        printf("%d/%d\n", a/b, c/b);
    }
    return 0;
}
