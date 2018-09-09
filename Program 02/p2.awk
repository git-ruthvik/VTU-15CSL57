BEGIN{
count=0;
}
{
if($1=="d")
count++
}
END{
printf("The total number of Packets Dropped :  %d \n",count)
} 




