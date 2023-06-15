import java.text.DecimalFormat;

abstract class FiguraGeometrica {
    protected double area;
    protected double perimetro;
    public abstract void calcularArea();
    public abstract void calcularPerimetro();
    public double getArea() {
        return area;
    }
    public double getPerimetro() {
        return perimetro;
    }
}
class Circulo extends FiguraGeometrica {
    private double radio;
    public Circulo(double radio) {
        this.radio = radio;
    }
    @Override
    public void calcularArea() {
        area = Math.PI * radio * radio;
    }
    @Override
    public void calcularPerimetro() {
        perimetro = 2 * Math.PI * radio;
    }
    public double getRadio() {
        return radio;
    }
}
class Cuadrado extends FiguraGeometrica {
    private double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }
    @Override
    public void calcularArea() {
        area = lado * lado;
    }
    @Override
    public void calcularPerimetro() {
        perimetro = 4 * lado;
    }
    public double getLado() {
        return lado;
    }
}
class Rectangulo extends FiguraGeometrica {
    private double base;
    private double altura;
    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }
    @Override
    public void calcularArea() {
        area = base * altura;
    }
    @Override
    public void calcularPerimetro() {
        perimetro = 2 * (base + altura);
    }
    public double getBase() {
        return base;
    }
    public double getAltura() {
        return altura;
    }
}
class Triangulo01 extends FiguraGeometrica {
    private double base;
    private double altura;
    private double hipotenusa;
    public Triangulo01(double base, double altura, double hypot) {
        this.base = base;
        this.altura = altura;
        this.hipotenusa = Math.hypot(base, altura);
    }
    @Override
    public void calcularArea() {
        area = (base * altura) / 2;
    }
    @Override
    public void calcularPerimetro() {
        perimetro = base + altura + hipotenusa;
    }
    public double getBase() {
        return base;
    }
    public double getAltura() {
        return altura;
    }
    public double getHipotenusa() {
        return hipotenusa;
    }
}
class Triangulo02 extends FiguraGeometrica {
    private double base;
    private double altura;
    private double hipotenusa;
    public Triangulo02(double base, double altura) {
        this.base = base;
        this.altura = altura;
        this.hipotenusa = Math.hypot(base, altura);
    }
    @Override
    public void calcularArea() {
        area = (base * altura) / 2;
    }
    @Override
    public void calcularPerimetro() {
        perimetro = base + altura + hipotenusa;
    }
    public double getBase() {
        return base;
    }
    public double getAltura() {
        return altura;
    }
    public double getHipotenusa() {
        return hipotenusa;
    }
}
public class Parcela {
    private FiguraGeometrica[] figuras;
    private double precioMetro2;
    public Parcela() {
        figuras = new FiguraGeometrica[5];
        figuras[0] = new Circulo(2);
        figuras[1] = new Cuadrado(4);
        figuras[2] = new Rectangulo(8, 4);
        figuras[3] = new Triangulo01(2, 3, Math.hypot(2, 3));
        figuras[4] = new Triangulo02(6.93, 6);
        precioMetro2 = 32;
    }
    public void calcularAreaTotal() {
        double areaTotal = 0;
        for (FiguraGeometrica figura : figuras) {
            figura.calcularArea();
            areaTotal += figura.getArea();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Área total de la parcela: " + df.format(areaTotal) + " m2.");
    }
    public void calcularPerimetroTotal() {
        double perimetroTotal = 0;
        for (FiguraGeometrica figura : figuras) {
            figura.calcularPerimetro();
            if (figura instanceof Cuadrado) {
                perimetroTotal += ((Cuadrado) figura).getPerimetro() - 4 - 2;
            } else if (figura instanceof Triangulo01) {
                perimetroTotal += ((Triangulo01) figura).getAltura() + ((Triangulo01) figura).getHipotenusa();
            } else if (figura instanceof Rectangulo) {
                perimetroTotal += (((Rectangulo) figura).getBase() - 6.93 + 4);
            } else if (figura instanceof Circulo) {
                perimetroTotal += 2 * Math.PI * ((Circulo) figura).getRadio();
            } else if (figura instanceof Triangulo02) {
                perimetroTotal += 2 * ((Triangulo02) figura).getBase();
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Perímetro total de la parcela: " + df.format(perimetroTotal) + " metros lineales.");
    }
    public void calcularCosteTotal() {
        double areaTotal = 0;
        for (FiguraGeometrica figura : figuras) {
            figura.calcularArea();
            areaTotal += figura.getArea();
        }
        double costeTotal = areaTotal * precioMetro2;

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Coste total de la parcela: " + df.format(costeTotal) + " €.");
    }
    public static void main(String[] args) {
        Parcela parcela = new Parcela();
        parcela.calcularAreaTotal();
        parcela.calcularPerimetroTotal();
        parcela.calcularCosteTotal();
    }
}