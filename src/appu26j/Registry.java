package appu26j;

import appu26j.exceptions.NoPermsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Registry
{
    private static final boolean PERMS = hasPerms();

    public static String execute(String command) throws IOException, NoPermsException
    {
        if (!PERMS)
        {
            throw new NoPermsException();
        }

        if (!command.contains("REG"))
        {
            command = "REG " + command;
        }

        if (!command.contains("/F"))
        {
            command += " /F";
        }

        Process process = Runtime.getRuntime().exec(command);
        StringBuilder output = new StringBuilder();

        try (InputStream inputStream = process.getInputStream(); InputStreamReader inputStreamReader = new InputStreamReader(inputStream); BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
        {
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    output.append(line).append("\n");
                }
            }
        }

        return output.substring(0, output.length() - 1);
    }

    public static boolean hasPerms()
    {
        if (System.getProperty("os.name") == null || !System.getProperty("os.name").toLowerCase().contains("win"))
        {
            return false;
        }

        try
        {
            Process process = Runtime.getRuntime().exec("reg query \"HKU\\S-1-5-19\"");
            process.waitFor();
            return process.exitValue() == 0;
        }

        catch (Exception e)
        {
            return false;
        }
    }
}
