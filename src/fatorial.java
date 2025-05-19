public class fatorial extends calculo {
    double res;
    double fat;

    @Override
    public double calcular(double a) {
            if (a == 0) {
            res = 1;
        } else if (a > 0) {
            res = calcular(a - 1);
            fat = a * res;
            res = fat;
        }
        return res;
    }
}
