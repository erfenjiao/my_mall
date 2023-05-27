# my_mall
电商系统-谷粒商场项目实战学习
p12 配置maven jdk
p13 项目结构构建 微服务模块




## 一些问题解决方法记录
### 1 
java: 找不到符号
符号: 方法 setOperation(java.lang.String)
解决：把lombok.version的版本调高了之后就可以了 调到1.18.20
### 2 数据库连接
为了创建一个非特权用户并授予其所需的权限，您可以按照以下步骤进行操作：

1. 以root用户身份登录到MySQL数据库中。

2. 创建一个新用户（假设用户名为newuser）并分配密码：

   ```
   CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';
   ```

   其中，localhost表示该用户只能从本地连接到MySQL服务器。如果您希望该用户能够从任意主机连接到MySQL服务器，则应将localhost替换为%。

3. 授予该用户所需的数据库访问权限：

   ```
   GRANT ALL PRIVILEGES ON dbname.* TO 'newuser'@'localhost';
   ```

   其中，dbname是您要授权给该用户的数据库名称。ALL PRIVILEGES表示该用户将获得该数据库的所有权限。

4. 刷新MySQL权限表以使更改生效：

   ```
   FLUSH PRIVILEGES;
   ```

5. 然后，您可以使用新用户的凭据进行数据库连接。

   ```
   mysql -u newuser -p
   ```

   然后输入密码即可登录到新用户的账户中。

请注意，为了提高系统的安全性，建议不要将密码直接存储在配置文件中，而是使用环境变量或其他加密措施来存储密码。