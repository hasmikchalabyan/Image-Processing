As I've applied median filter and after that gaussian filter, my matrix will not be changed, except the center number. 
Applying at first median , will compute the median of neighbours, which will change the centered number to -1, and after that
gaussian blur uses average of neighbours, so it will stay unchanged. 
