echo off
java CT
echo errorlevel = %errorlevel%
if %errorlevel% == 1 (
    echo 「CT.class」ファイルはありますか
    echo CLASSPATH の先頭が「.」で開始していますか
    echo ※環境変数の編集後は，コマンドプロンプトを閉じて，開き直してください
)
