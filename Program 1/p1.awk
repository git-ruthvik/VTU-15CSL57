BEGIN{
count=0;
}
{
if($1=="d")
count++
}
END{
printf("Total Number Of Packets Dropped : %d \n\n",count)
}

