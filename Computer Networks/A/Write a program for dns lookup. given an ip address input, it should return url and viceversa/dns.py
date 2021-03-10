import socket
print("Enter 1 for URL to IP")
print("Enter 2 for IP to Hostname")
k = int(input("Enter Choice:"))
if k == 1 :
    URL = input("Enter URL:")
    print(socket.gethostbyname(URL))
else :
    IP = input("Enter IP:")
    print(socket.gethostbyaddr(IP))