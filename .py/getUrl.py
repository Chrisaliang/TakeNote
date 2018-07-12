import urllib.request
import urllib.parse


url = "http://www.baidu.com"
header = {
    'connection': "keep-alive",
    'cache-control': "no-cache",
    'upgrade-insecure-requests': "1",
    'user-agent': "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36",
    'accept': "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    'accept-language': "zh-CN,en-US;q=0.8,en;q=0.6"
}

request = urllib.request.Request(url,headers=header)
response = urllib.request.urlopen(request).read()

# data = baidu.read()

# print(data)

# dataline = baidu.readline()

fhandle = open("./baidu.html","wb")

fhandle.write(response)

fhandle.close

