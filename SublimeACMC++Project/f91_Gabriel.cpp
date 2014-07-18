#include<stdio.h>
int main(){
int num;
scanf("%d",&num);
while(num!=0){
if(num<=100){
printf("f91() = 91");
}
else{
printf("f91("+num+") = "+(num-10));
}
scanf("%d",&num);
}
return 0;
}
