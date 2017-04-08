import java.util.Scanner;

/**
 * Created by Administrator on 2017/4/8.
 */
public class test {
    Scanner op=new Scanner(System.in);
    String[] re1=new String[2];
    String[] re2=new String[2];
    int r1,r2,r3,r4;

    String huajian(String text){

        if(text.indexOf("/")!=-1) {
            re1 = text.split("/");
            r1 = Integer.parseInt(re1[0]);
            r2 = Integer.parseInt(re1[1]);

            if (r1 == 0)
                return "0";
            else {
                for (int i = 1; i <= r1; i++) {
                    if (r1 % i == 0 && r2 % i == 0) {
                        r1 = r1 / i;
                        r2 = r2 / i;
                        i = 1;
                    }
                    if (r2 == 1)
                        return r1 + "";
                }
                return r1+"/"+r2;
            }

        }
        else
            return text;

    }

    public static void main(String[] args) {
/*        String[] aa=new String[2];
        String a="ttt/o";
        aa=a.split("/");
        System.out.println(aa[0]);
        System.out.println(aa[1]);
        System.out.println(huajian(32,4));
        String aa="213/54";
        if(aa.indexOf("/")!=-1)  */
        test aa=new test();
        System.out.println(aa.huajian("1/4"));



    }
}
