import java.io.*;
import java.util.Scanner;

import java.io.IOException;

public class Qwe {

    private static String FormatedLine (String line, int Length)
    {
        int j = (Length - line.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < j; i++ )
        {
            sb.append(" ");
        }
        sb.append(line);
        for (int i = 0; i < j; i++ )
        {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException
    {

        Scanner sc = new Scanner(System.in);
        cycle:
        while (true)
        {

            String x = sc.nextLine().trim();
            switch (x) {
                case ("print"):
                {
                    try
                    {
                        String fileName;
                        fileName = sc.nextLine();
                        FileReader fr = new FileReader(fileName);
                        Scanner Filesc = new Scanner(fr);


                        while (Filesc.hasNextLine())
                        {
                            String line = Filesc.nextLine();
                            System.out.println(line);
                        }
                    }
                    catch (Exception ex)
                    {

                        break;
                    }

                    break;
                }
                case ("format"):
                {
                    try
                    {
                        String fileName;
                        fileName = sc.nextLine();
                        FileReader fr = new FileReader(fileName);
                        Scanner Filesc = new Scanner(fr);
                        int i = 1;
                        int Length = 0;
                        while (Filesc.hasNextLine()) {
                            String line = Filesc.nextLine();
                            Length = Math.max(Length, line.length());
                        }
                        Filesc.close();
                        fr = new FileReader(fileName);
                        Filesc = new Scanner(fr);
                        while (Filesc.hasNextLine())
                        {
                            String line = Filesc.nextLine();
                            if (!line.isEmpty())
                            {
                                line = line.substring(0, 1).toUpperCase() + line.substring(1);
                            }
                            line = FormatedLine(line, Length);
                            if (line.trim().isEmpty())
                            {
                                continue;
                            }
                            else
                            {
                                if (i % 2 == 0)
                                {
                                    System.out.println(line);
                                    System.out.println();
                                    i++;
                                }
                                else
                                {
                                    System.out.println(line);
                                    i++;
                                }
                            }

                        }
                        Filesc.close();
                    }
                    catch (Exception ex)
                    {
                        break;
                    }
                    break;
                }
                case ("exit"):
                {
                    break cycle;
                }
                default:
                {
                    System.out.println("Error");
                    break;
                }
            }

        }
        sc.close();
    }

}
