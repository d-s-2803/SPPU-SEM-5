#include<sys/types.h>
#include<sys/socket.h>
#include<stdio.h>
#include<netinet/in.h>
#include <unistd.h>
#include<string.h>
#include <arpa/inet.h>

void main()
{
 int b,sockfd,connfd,sin_size,l,n,len;
 char operator;
 int op1,op2,result;
 if((sockfd=socket(AF_INET,SOCK_STREAM,0))>0)
  printf("socket created sucessfully\n");  //socket creation
 struct sockaddr_in servaddr;           
 struct sockaddr_in clientaddr;

 servaddr.sin_family=AF_INET;
 servaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
 servaddr.sin_port=6006;

 if((bind(sockfd, (struct sockaddr *)&servaddr,sizeof(servaddr)))==0)
 printf("bind sucessful\n");  
 
 if((listen(sockfd,5))==0) //listen for connections on a socket
  printf("listen sucessful\n");
 
 sin_size = sizeof(struct sockaddr_in);
 if((connfd=accept(sockfd,(struct sockaddr *)&clientaddr,&sin_size))>0);
 printf("accept sucessful\n");
 
 read(connfd, &operator,10);
 read(connfd,&op1,sizeof(op1));
 read(connfd,&op2,sizeof(op2));
 
 switch(operator)
 {
  case '+':
   result=op1 + op2;
    printf("Result is: %d + %d = %d\n",op1, op2, result);
    break;
  case '-':
   result=op1 - op2;
          printf("Result is: %d - %d = %d\n",op1, op2, result);
          break;
  case '*':
   result=op1 * op2;
          printf("Result is: %d * %d = %d\n",op1, op2, result);
          break;
  case '/':
   result=op1 / op2;
          printf("Result is: %d / %d = %d\n",op1, op2, result);
          break;
  default:
          printf("ERROR: Unsupported Operation");
     }
 write(connfd,&result,sizeof(result)); 
 close(sockfd);

}