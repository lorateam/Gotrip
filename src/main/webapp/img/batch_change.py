#Author chanchan

import shutil

import os

g = os.walk('/home/chanchan/Desktop/minsu/web/img/productSingle_small')

for path,dir_list,file_list in g:
    for file_name in file_list:
        # print(os.path.join(path, file_name) )
        print(file_name)

src='/home/chanchan/Desktop/minsu/web/img/a.jpg'
for file_name in file_list:
        dst='/home/chanchan/Desktop/minsu/web/img/productSingle_small/'+file_name
        shutil.copyfile(src,dst)

'''
遍历所有文件替换成其他
'''
