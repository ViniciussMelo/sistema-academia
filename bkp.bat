@echo on
set path_old = %path%
set path = "C:\Program Files\MySQL\MySQL Server 5.7\bin\"
mysqldump -u root -p1 Unesc > "C:\Users\luis_justus\Documents\sandbox\sistema-academia\Backup\"Unesc.sql
set path = path_old
exit
