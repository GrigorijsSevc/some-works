// 241RDB052 Grigorijs Sevcenko 3

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
public class Main {

    private static void comp(String name)
    {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 0b00);
        map.put("C", 0b01);
        map.put("G", 0b10);
        map.put("T", 0b11);

        StringBuilder sb = new StringBuilder();
        for (int i =0; i< name.length(); i++)

        {
            char symbol = Character.toUpperCase(name.charAt(i));
            Integer bcode = map.get(String.valueOf(symbol));
            if (bcode != null )
            {
                sb.append(String.format("%2s",Integer.toBinaryString(bcode)).replace(' ', '0'));
            }
            else
            {
                System.out.println("wrong command format");
                return;
            }
        }
        String binStr = sb.toString();

        if (binStr.length() % 8 != 0) {
            int y = 8 - (binStr.length() % 8);
            binStr = binStr + "0".repeat(y);
        }
        int Decstr = Integer.parseInt(binStr, 2);
        if (Decstr==0)
        {
            System.out.print(name.length() + " ");
            System.out.println("0");
        }
        else
        {
            String Hexstr = Integer.toHexString(Decstr);
            Hexstr = Hexstr.replace('0', ' ');
            System.out.print(name.length() + " ");
            System.out.println(Hexstr.toUpperCase());
        }
    }
    private static void decomp (int[] bytes)
    {
        HashMap<String, Character> map = new HashMap<>();
        map.put("00", 'A');
        map.put("01", 'C');
        map.put("10", 'G');
        map.put("11", 'T');


            Integer[] bytesObjectArray = Arrays.stream(bytes).boxed().toArray(Integer[]::new);
            int n = bytes[0];
            ArrayList<Integer> newBytes = new ArrayList<>(Arrays.asList(bytesObjectArray));
            newBytes.remove(0);
            StringBuilder bs = new StringBuilder();
            for (int b : newBytes) {
                String binary = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                bs.append(binary);
            }


            StringBuilder DnsSymbol = new StringBuilder();
            for (int i = 0; i < bs.length(); i += 2) {
                String bits = bs.substring(i, i + 2);
                Character dna = map.get(bits);
                if (dna != null) {
                    DnsSymbol.append(dna);
                }
            }


            StringBuilder Hexstr = new StringBuilder();
            for (int b : bytes) {
                Hexstr.append(String.format("%X", b & 0xFF)).append(" ");
            }
            String resultDns = DnsSymbol.toString();

            System.out.println(Hexstr.toString().trim());
            if (n>resultDns.length())
            {
                System.out.println(resultDns);
            }
            else
            {
                System.out.println(resultDns.substring(0, n));
            }


    }
    private static void about()
    {
        System.out.println("241RDB052 Grigorijs Sevcenko 3");
    }
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        cycle:
        while (true) {
            String x = sc.next();
            switch (x) {
                case ("comp"): {
                    String name = sc.next();
                    comp(name);
                    break;
                }
                case ("decomp"): {
                    try {
                        int length = sc.nextInt();

                        if (length <= 0) {
                            System.out.println("wrong command format");
                            break;
                        }
                        int[] bytes = new int[length];
                        for (int i = 0; i < length; i++) {
                            bytes[i] = sc.nextInt();
                        }

                        decomp(bytes);
                        break;
                    }
                    catch (InputMismatchException e) {
                        System.out.println("wrong command format.");
                        sc.next();
                        break;
                    }
                }
                case ("about"): {
                    about();
                    break;
                }
                case ("exit"): {
                    break cycle;
                }
                default: {
                    System.out.println("wrong command");
                    break;
                }
            }
        }


    }
}