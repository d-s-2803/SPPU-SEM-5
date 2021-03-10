import random
a = list(map(int,input("\nEnter the numbers in the frame in a sequence with blank spaces between two numbers: ").strip().split()))
n = int(input("Enter window size: "))
count = int(len(a) / 4) 
i = 0
while i < len(a):
    j = 0 
    for _ in range(n) :
        if i < len(a):
            print("Sent frame " + str(a[i]))
            i,j = i + 1,j+1
    i = i - j 
    for _ in range(n) :
        x = random.randint(0,1)
        if x == 0 and count != 0 and i < len(a):
            print("Frame " + str(a[i]) + " not recieved.Transmit the frame again")
            count = count - 1 
            break 
        elif i < len(a) :
            print ("Acknowledgment recieved for Frame " + str(a[i]))
            i = i + 1 
            
        
    

    
    
