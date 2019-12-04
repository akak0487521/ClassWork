#include<stdio.h>
#include<conio.h>
#include <time.h>
#include <stdlib.h>
int min(int,int);
void floyds(int p[1000][1000],int n) {
	int i,j,k;
	clock_t start, end;
	start = clock();
	for (k=1;k<=n;k++)
	  for (i=1;i<=n;i++)
	   for (j=1;j<=n;j++)
	    if(i==j)
	     p[i][j]=0; else
	     p[i][j]=min(p[i][j],p[i][k]+p[k][j]);
	     
	end = clock();
	double diff = end - start; // ms
    printf(" %f  ms" , diff);
}
int min(int a,int b) {
	if(a<b)
	  return(a); else
	  return(b);
}
void main() {
	int p[1000][1000],w,n = 1000,e = 3000,u,v,i,j;
	;
	for (i=1;i<=n;i++) {
		for (j=1;j<=n;j++)
		   p[i][j]=999;
	}
	for (i=1;i<=e;i++) {
	    if(p[rand() % 1000][rand() % 1000] > 10)
		    p[rand() % 1000][rand() % 1000]=(rand() % 10) + 1;
		else i--;
	}
	floyds(p,n);
	printf("\n The shortest paths are:\n");
	for (i=1;i<=n;i++)
	  for (j=1;j<=n;j++) {
		if(i!=j)
		    printf("\n <%d,%d>=%d",i,j,p[i][j]);
	}
	getch();
}
