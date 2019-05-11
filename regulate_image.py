
import os
import cv2 as cv

file_name_list = []

resize_width = 300

for filename in os.listdir("./image_directory"):
    print(filename)
    try:
        img = cv.imread("./image_directory/"+ filename, 1)
        print ("read success")
        im_height, im_width = img.shape[:2]
        print ("got width and height")
        img_resize = cv.resize(img, (resize_width, int(im_height*(resize_width/im_width))))
        print ("resize")
        cv.imwrite("./image_directory/"+ filename, img_resize)
    except:
        print ("error")