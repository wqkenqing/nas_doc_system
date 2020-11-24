#!/bin/python3
## nas文件系统化管理
import os
import sys
import elasticsearch
import json

from elasticsearch import Elasticsearch

sys.setdefaultencoding("utf8")


def getPathInfo(path):
    '''
    :param self:
    :param path:
    :desc:获取该路径下所有的文件信息
    :return:
    '''
    # 递归该路径下所有文件路径
    pathList = getPathAllFile(path)
    infoList = operatePathInfo(pathList)
    return infoList


def getPathAllFile(path):
    pathList = []
    # 切换工作路径
    for root, dirs, files in os.walk(path):
        for file in files:
            filePath = root + "/" + file
            pathList.append(filePath)
    return pathList


def operatePathInfo(pathList):
    '''
    :param pathList:
    :return:infoList
    '''
    infoList = []
    for pone in pathList:
        pathFileInfoMap = {}
        pathArray = str(pone).split("/")
        pathFile = pathArray[len(pathArray) - 1]
        if (pathFile.__contains__(".")):
            pathFileName = pathFile.split(".")[0]
            if (pathFileName != ''):
                pathFileSuffix = pathFile.split(".")[1]
                pathFileInfoMap.__setitem__("pbase", pone)
                pathFileInfoMap.__setitem__("pathFile", pathFile)
                pathFileInfoMap.__setitem__("pathFileName", pathFileName)
                pathFileInfoMap.__setitem__("pathFileSuffix", pathFileSuffix)
            else:
                pathFileInfoMap.__setitem__("pbase", pone)
                pathFileInfoMap.__setitem__("pathFile", pathFile)
        infoList.append(pathFileInfoMap)
    return infoList


def connectES():
    es = Elasticsearch(
        ['192.168.3.9'],
        port=9200
    )
    return es


def putInfoIntoES(indexName, fileInfoMapList):
    es = connectES()
    for fileMap in fileInfoMapList:
        es.index(index=indexName, body=fileMap)

    return None


if __name__ == '__main__':
    # path=sys.argv[1]
    path = "/Users/kuiqwang/Desktop/hexo"
    infoList=getPathInfo(path)
    putInfoIntoES("nas_files",fileInfoMapList=infoList)
