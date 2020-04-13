# TakeNote
this maybe an android note app or other type

# MacBook Pro brew install

## /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"

如果没有成功安装home-brew core

cd /usr/local/Homebrew/Library/Taps/homebrew

git clone https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git

如果git失败，一般是因为git限制了推送数据的大小导致的错误，解决方案有两种
 1 重新设置全局的通信缓存大小，增加git缓冲区大小（亲测有效）
      git config --global http.postBuffer 524288000
      git config --list
 2 浅层克隆，深度设置为1
      git clone http://github.com/target.git --depth 1  # target.git 为目标地址
      cd target  #先进入库路径下
      git fetch --unshallow # 获取完整库


## 替换homebrew源
   
    替换homebrew默认源
    
    cd "$(brew --repo)"
    git remote set-url origin git://mirrors.ustc.edu.cn/brew.git
    
    替换homebrew-core源
    
    cd "$(brew --repo)/Library/Taps/homebrew/homebrew-core"
    git remote set-url origin git://mirrors.ustc.edu.cn/homebrew-core.git

## brew更新
    
    brew update

设置 bintray镜像
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.ustc.edu.cn/homebrew-bottles' >> ~/.bash_profile
source ~/.bash_profile

