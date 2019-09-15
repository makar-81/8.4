package graphs;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try {
        String temp;

        FileReader fr = new FileReader("./test1.txt");
        FileWriter wr = new FileWriter("./test2.txt");

        BufferedWriter  bw = new BufferedWriter(wr);
        BufferedReader br = new BufferedReader(fr);

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            List<List<String>> final_store = new ArrayList<>();
            List<List<String>> begin_store = new ArrayList<>();
            int line_num = 0;

            try {
            while ((temp = br.readLine()) != null) {
                while (!temp.isEmpty()) {
                    int pos1 = temp.indexOf("\t");
                    int pos2 = temp.indexOf("\t",pos1+1);
                    ArrayList<String> line = new ArrayList<String>();
                    if (pos1 > 0 && pos2>0) {
                            line.add(temp.substring(0,pos1));
                            line.add(temp.substring(pos1+1,pos2));
                            line.add(temp.substring(pos2+1,temp.length()));
                            begin_store.add(line);
                        }
                    temp="";
                    }
                }
                for (int i=0;i<begin_store.size();i++) {
                    if (!(begin_store.get(i)).get(2).equals("{}")) {
                        final_store.add(begin_store.get(i));

                        String temp_string = (begin_store.get(i)).get(2).substring(1, (begin_store.get(i)).get(2).length() - 1);
                        int count = 1;
                        while (temp_string.contains(",")) {
                            count++;
                            int pos3 = temp_string.indexOf(",");
                            temp_string = temp_string.substring(pos3 + 1, temp_string.length());
                        }

                        temp_string = (begin_store.get(i)).get(2).substring(1, (begin_store.get(i)).get(2).length() - 1);

                        // начинаем разбирать вершины соседние и вносить в финальный массив
                        while (!temp_string.isEmpty()){
                            List<String> temp_list = new ArrayList<>();
                            int pos3 = temp_string.indexOf(",");
                            if (pos3 > 0) {
                                temp_list.add(temp_string.substring(0, pos3));
                                Double rank = Double.parseDouble((begin_store.get(i)).get(1)) / count;
                                Double newDouble = new BigDecimal(rank).setScale(3, RoundingMode.HALF_EVEN).doubleValue();
                                DecimalFormatSymbols s = new DecimalFormatSymbols();
                                s.setDecimalSeparator('.');
                                DecimalFormat myFormatter = new DecimalFormat("0.000",s);
                                String output = myFormatter.format(newDouble);
                                temp_list.add(output);
                                temp_list.add("{}");
                                final_store.add(temp_list);
                                temp_string = temp_string.substring(pos3+1,temp_string.length());

                            } else {
                                temp_list.add(temp_string.substring(0,temp_string.length()));
                                Double rank = Double.parseDouble((begin_store.get(i)).get(1)) / count;
                                Double newDouble = new BigDecimal(rank).setScale(3, RoundingMode.HALF_EVEN).doubleValue();
                                DecimalFormatSymbols s = new DecimalFormatSymbols();
                                s.setDecimalSeparator('.');
                                DecimalFormat myFormatter = new DecimalFormat("0.000",s);
                                String output = myFormatter.format(newDouble);
                                temp_list.add(output);
                                temp_list.add("{}");
                                final_store.add(temp_list);
                                temp_string = "";
                            }
                        }
                    }



                }
                for (int j = 0; j < final_store.size(); j++) {
                   System.out.println((final_store.get(j)).get(0) + "\t" + (final_store.get(j)).get(1) + "\t" + (final_store.get(j)).get(2) + "\n");
                    bw.write((final_store.get(j)).get(0) + "\t" + (final_store.get(j)).get(1) + "\t" + (final_store.get(j)).get(2) + "\n");
                    bw.flush();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

