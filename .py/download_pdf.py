#!/usr/bin /env  python
# coding:utf-8 
"""
@version:  2.7.14
@author:  Long
@file:  download_pdf.py
@time: 2018/6/20 15:44
"""

# import Queue

import urllib
import requests
from bs4 import BeautifulSoup

# url = Queue.queue()
request_headers = {
    'connection': "keep-alive",
    'cache-control': "no-cache",
    'upgrade-insecure-requests': "1",
    'user-agent': "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36",
    'accept': "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    'accept-language': "zh-CN,en-US;q=0.8,en;q=0.6"
}


def download_pdf(url):
    response = requests.get(url, headers=request_headers)
    html_content = response.content
    soup = BeautifulSoup(html_content, 'html.parser')
    pdf_url = soup.findAll('a')
    print(len(pdf_url))
    for index, ur in enumerate(pdf_url):

        if index >= 1:
            URL = url + ur['href']
            print(URL)
            getFile(URL,ur['href'])


def getFile(url, name):
    u = urllib.request.urlopen(url)
    f = open("./pdf/" + name, 'wb')

    block_sz = 8192
    while True:
        buffer = u.read(block_sz)
        if not buffer:
            break

        f.write(buffer)
    f.close()
    print("Successful to download" + " " + url)


if __name__ == "__main__":
    url = "http://www.dfki.uni-kl.de/~bukhari/data/papers/"
    download_pdf(url)
