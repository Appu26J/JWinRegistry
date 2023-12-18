<img width="360" alt="image" src="https://github.com/Appu26J/JWinRegistry/assets/128838345/f989604b-cfb8-4503-9d29-60ee3b0be99f">

A Java library which allows you to execute Windows registry commands in Java, in just one line of code.  
It's just like how you use the *REG* command in Command Prompt.

### Example Usage
```java
Registry.execute("ADD HKEY_LOCAL_MACHINE\SOFTWARE\MyJavaApp");
```
*Example class based on this library:*
- [AppUtil.java](https://github.com/Appu26J/Softuninstall/blob/main/src/appu26j/utils/AppUtil.java)

**WARNING**: If you execute a command that usually requires confirmation in command prompt, the current thread will just hang.  
*To fix this, just add /F at the end of the command*

### Library Download
https://github.com/Appu26J/JWinRegistry/releases/download/WindowsRegistry/jwinregistry.jar
