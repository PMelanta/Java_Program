package com.melanta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        int a, b, n = 0, i, j, t = 0, c = 0, m, unit;
        a = Integer.parseInt(in.readLine());
        int ans1[] = new int[a + 1];
        int ans2[] = new int[a + 1];
        for (b = 1; b <= a; b++) {
            m = Integer.parseInt(in.readLine());
            n = rev(m);
            unit = 1;
            while (n >= 1) {
                t = n % 10;
                if (t == 4) {
                    ans1[b] = ans1[b] + (2 * unit);
                    ans2[b] = ans2[b] + (2 * unit);
                } else {
                    ans2[b] = ans2[b] + (t * unit);
                }
                n = n / 10;
                unit = 10 * unit;
            }
            ans1[b] = rev(ans1[b]);
            ans2[b] = rev(ans2[b]);
        }
        for (b = 0; b < a; b++)
            System.out.println("Case #" + (b + 1) + ": " + ans1[b + 1] + " " + ans2[b + 1]);
    }

    private static int rev(int m) {
        int n = 0;
        while (m >= 1) {
            n = n * 10;
            n = (m % 10) + n;
            m = m / 10;
        }
        return n;
    }
}
