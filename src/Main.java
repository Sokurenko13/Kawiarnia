public class Main{
    public static void main(String[] args) {
        ProduktMenu p1 = new ProduktMenu("C-01", "Flat White", 35.0, "coffe");
        ProduktMenu p2 = new ProduktMenu("C-02", "Espresso", 17.0, "coffe");
        ProduktMenu p3 = new ProduktMenu("D-01", "Chesscake", 43.0, "desert");
        ProduktMenu p4 = new ProduktMenu("D-02", "Tiramissu", 40.0, "desert");
        KlientKawiarni klient = new KlientKawiarni(100, "Igor", "Burlaew", "igorb@mail.pl");
        Zamowienie zamowienie = new Zamowienie(klient);
        zamowienie.dodajProdukt(p1);
        zamowienie.dodajProdukt(p2);
        zamowienie.dodajProdukt(p3);
        zamowienie.dodajProdukt(p4);
        System.out.println(klient);
        System.out.println(zamowienie);
        System.out.println("Laczna wartosc:" + zamowienie.policzWartosc());
        System.out.println("Liczba pozycji" + zamowienie.policzLiczbeProduktow());
        System.out.println("Liczba produktow utworzonych w systeme:" + ProduktMenu.getLiczbaProduktow());
        ProduktMenu kopiaFlatWhite = new ProduktMenu("C-01", "Flat White duplikat", 35.0, "coffe");
        System.out.println("Czy produkty sa rowne?" + p1.equals(kopiaFlatWhite));
        zamowienie.oznaczJakoOplacone();
        System.out.println(zamowienie);


    }
}
public class ProduktMenu {
    private String kod;
    private String nazwa;
    private double cena;
    private String kategoria;
    private static int liczbaProduktow=0;
    public ProduktMenu(String kod, String nazwa, double cena, String kategoria){
        this.kod=kod;
        this.nazwa=nazwa;
        this.cena=cena;
        this.kategoria=kategoria;
        liczbaProduktow++;
    }
    public static int getLiczbaProduktow() {
        return liczbaProduktow;
    }
    public double getCena(){
        return cena;
    }
    @Override
    public String toString(){
        return nazwa+"("+kod+")-"+cena+"zl";
    }
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        ProduktMenu that = (ProduktMenu) o;
        return kod.equalsIgnoreCase(that.kod);
    }
}
public class KlientKawiarni {
    private int idKlienta;
    private String imie;
    private  String nazwisko;
    private String email;
    public KlientKawiarni(int idKlienta, String imie, String nazwisko, String email){
        this.idKlienta=idKlienta;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
    }
    @Override
    public  String toString(){
        return imie+""+nazwisko+"["+email+"]";
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        KlientKawiarni that = (KlientKawiarni) o;
        return idKlienta== that.idKlienta || email.equals(that.email);
    }
}
private int numerZamowinie;
private KlientKawiarni klient;
private ArrayList<ProduktMenu>produkty;
private  boolean oplacone;
private  static  int kolejnyNumer=1;
public Zamowienie(KlientKawiarni klient){
    this.numerZamowinie=kolejnyNumer++;
    this.klient=klient;
    this.produkty=new ArrayList<>();
    this.oplacone=false;
}
public void dodajProdukt(ProduktMenu produkt){
    this.produkty.add(produkt);
}
public double policzWartosc(){
    double summa=0;
    for (ProduktMenu p:produkty){
        summa+=p.getCena();
    }
    return summa;
}
public int policzLiczbeProduktow(){
    return produkty.size();
}
public void oznaczJakoOplacone(){
    this.oplacone=true;
}
@Override
public String toString(){
    String status=oplacone?"TAK":"NIE";
    return "Zamowienie numer"+numerZamowinie+"|Klient:"+klient+"|Produkty:"+produkty+"|Oplacono:"+status;
}