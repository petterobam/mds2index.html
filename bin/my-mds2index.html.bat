@Echo Off
:: author:petterobam
:: url:https://github.com/petterobam/my-mds2index.html
@Title my-mds2indexhtml@petterobam
Rem CenterSelf
:config_load
echo "#############################################################"
echo ">>>>>>>>>>>config"
:: bat current Path
Set curr_path=%~dp0
:: remove bin/
Set curr_path=%curr_path:~0,-4%
echo ">>>>>>>>>>>curr_path %curr_path%"
:: jar File Path
set jar_path=%curr_path%my-mds2index.html-1.0.0.jar
:: replace \ to /
Set curr_path=/%curr_path:\=/%
:: Config File Path
set conf_path=%curr_path%config/config.yml
:: MarkDown Files Floder Path
set mds_path=%curr_path%markdowns
:: index.html Tpl File Path
set tpl_path=%curr_path%template/index.html
:: Output File Path
set out_path=%curr_path%output/index.html
echo "#############################################################"
echo ">>>>>>>>>>>fill config..."
echo "--------------------------"
echo ">>>>>>>>>>>Config File Path:%conf_path%"
echo "--------------------------"
echo ">>>>>>>>>>>MarkDown Files Floder Path:%mds_path%"
echo "--------------------------"
echo ">>>>>>>>>>>index.html Tpl File Path:%tpl_path%"
echo "--------------------------"
echo ">>>>>>>>>>>Output File Path:%out_path%"
echo "--------------------------"
echo ">>>>>>>>>>>finish config!!"
echo "#############################################################"
echo ">>>>>>>>>>>mds2index.html jar File Path:%jar_path%"
echo "------------------------------------------------------------"
:mds2indexhtml
echo ">>>>>>>>>>>starting convert..."
echo "#############################################################"
echo "run java -jar %jar_path% %conf_path% %mds_path% %tpl_path% %out_path% >> my-html2file.log 2>> my-html2file-error.log"
java -jar %jar_path% %conf_path% %mds_path% %tpl_path% %out_path% >> my-html2file.log 2>> my-html2file-error.log
echo "#############################################################"
echo ">>>>>>>>>>>convert over!!"
echo "------------------------------------------------------------"
Pause
Exist