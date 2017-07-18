#! /bin/sh
targetDir="/data/mybatisgen"
sourceDir="src/test/resources"
init(){
  echo "开始初始化"
  refresh;
  copy;
  echo "初始化成功"
}

refresh(){
  echo "开始刷新"
  command -v mvn >/dev/null 2>&1 || { echo >&2 "I require mvn but it's not installed.  Aborting."; exit 1; }
  mvn assembly:assembly
  cp -rf target/mybatisgen-0.0.1.jar $sourceDir
  echo "刷新成功"
}
# 复制目录
copy(){
  echo "开始复制"
if [ ! -d "$targetDir" ]; then
	mkdir "$targetDir"
fi
 cp -rf $sourceDir/* $targetDir
 echo "复制成功"
}
if [ ! -n "$1" ] ;then  
    echo "请输入参数:
          -i 初始化
          -r 刷新
          -c 复制
    "  
else  
while [ -n "$1" ]; do 
case $1 in
 -i) init;break;;
 -r) refresh;break;;
 -c) copy;break;;
esac
done
fi  


