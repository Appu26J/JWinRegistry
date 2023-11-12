package appu26j;

import appu26j.exceptions.NoPermsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Registry
{
    public static String execute(String command) throws IOException, NoPermsException
    {
        if (!command.contains("REG"))
        {
            command = "REG " + command;
        }

        Process process = Runtime.getRuntime().exec(command);
        StringBuilder output = new StringBuilder();
        boolean accessDenied = false;

        try (InputStream inputStream = process.getInputStream(); InputStreamReader inputStreamReader = new InputStreamReader(inputStream); BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
        {
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    if (line.equals("ERROR: Access is denied."))
                    {
                        accessDenied = true;
                        break;
                    }

                    output.append(line).append("\n");
                }
            }
        }

        if (accessDenied)
        {
            throw new NoPermsException();
        }

        return output.substring(0, output.length() - 1);
    }
}
