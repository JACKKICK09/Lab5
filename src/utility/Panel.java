package utility;

import Classes.*;
import Bugs.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Panel {
    private final double Min_X = -727;
    private final long Min_Y = - 427;
    private final int passpordIdLenght = 7;
    private final int Min_price = 1;

    private BufferedReader reader;

    private Scanner sc;
    private boolean status;

    public Panel(Scanner sc){
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.sc = sc;
        status = false;
    }

    public void setScan(Scanner sc){
        this.sc = sc;
    }

    public Scanner getScan(){
        return sc;
    }

    public void setFileStatus(){
        status = true;
    }

    public void setUserStatus(){
        status = false;
    }

    public String inputString(String prompt) {
        Console.println(prompt + " ");
        return sc.nextLine().trim();
    }

    public String InputName() throws InCorrectInput{
        String name;
        while (true) {
            try {
                System.out.println(" Введите Имя:");
                name = sc.nextLine().trim();
                if (status) System.out.println(name);
                if (name.equals("")) throw new MustBeNotEmpty();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror(" Имя не распознано!");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (MustBeNotEmpty exception) {
                Console.printerror("Имя не может быть пустым!");

            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    public double InputX() throws InCorrectInput{
        double x;
        String strX;
        while (true){
            try {
                Console.println("Введите X:");
                strX = sc.nextLine().trim();
                if (status) Console.println(strX);
                x = Double.parseDouble(strX);
                if (x < Min_X) throw new LimitException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("X не распознан");
                System.exit(0);
                if (status) throw new InCorrectInput();
            } catch (LimitException exception){
                Console.printerror("X не может быть меньше" + Min_X);
                if (status) throw new InCorrectInput();
            }
            catch (NumberFormatException exception) {
                Console.printerror("Неверный формат ввода");
                if (status) throw new InCorrectInput();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return x;
    }

    public long InputY() throws InCorrectInput{
        long y;
        String strY;
        while (true){
            try {
                Console.println("Введите Y:");
                strY = sc.nextLine().trim();
                if (status) Console.println(strY);
                y = Long.parseLong(strY);
                if (y < Min_Y) throw new LimitException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("X не распознан");
                if (status) throw new InCorrectInput();
                System.exit(0);
            }   catch (LimitException exception) {
                Console.printerror("Y не может быть меньше " + Min_Y + "!");
                if (status) throw new InCorrectInput();
            } catch (NumberFormatException exception) {
                Console.printerror("Неверный формат ввода");
                if (status) throw new InCorrectInput();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return y;
    }

    public Coordinates askCoordinates() throws InCorrectInput {
        double x;
        long y;
        x = InputX();
        y = InputY();
        return new Coordinates(x, y);
    }

    public float InputPrice() throws InCorrectInput{
        String strPrice;
        float price;
        while (true){
            try {
                Console.println("Введите цену:");
                strPrice = sc.nextLine().trim();
                if (status) Console.println(strPrice);
                price = Float.parseFloat(strPrice);
                if (price < Min_price) throw new LimitException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Цена не распознана");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (LimitException exception){
                Console.printerror("Цена не может быть меньше нуля");
                if (status) throw new InCorrectInput();
            }
            catch (NumberFormatException exception) {
                Console.printerror("Неверный формат ввода");
                if (status) throw new InCorrectInput();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return price;
    }

    public  long InputManufacture() throws InCorrectInput{
        String strManufactureCost;
        long manufactureCost;
        while (true){
            try {
                Console.println("Введите ManufactureCost:");
                strManufactureCost = sc.nextLine().trim();
                if (status) Console.println(strManufactureCost);
                manufactureCost = Long.parseLong(strManufactureCost);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("ManufactureCost не распознан");
                if (status) throw new InCorrectInput();
                System.exit(0);
            }
            catch (NumberFormatException exception) {
                Console.printerror("Неверный формат ввода");
                if (status) throw new InCorrectInput();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return manufactureCost;
    }

    public UnitOfMeasure InputUnitOfMeasure() throws InCorrectInput{
        String strUnitOfMeasure;
        UnitOfMeasure unitOfMeasure;
        while (true) {
            try {
                Console.println("Список единиц измерения - " + UnitOfMeasure.nameList());
                Console.println("Введите единицу измерения:");
                strUnitOfMeasure = sc.nextLine().trim();
                if (status) Console.println(strUnitOfMeasure);
                unitOfMeasure = UnitOfMeasure.valueOf(strUnitOfMeasure.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Единица измерения не распознана!");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (IllegalArgumentException exception) {
                Console.printerror("Такой единицы измерения нет в списке!");
                if (status) throw new InCorrectInput();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return unitOfMeasure;
    }

    public String InputPersonName() throws InCorrectInput{
        String name;
        while (true){
            try{
                Console.println("Введите имя");
                name = sc.nextLine().trim();
                if (status) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmpty();
                break;
            }catch (NoSuchElementException exception) {
                Console.printerror("Имя не распознано");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (MustBeNotEmpty exception) {
                Console.printerror("Имя не может быть пустым");
                if (status) throw new InCorrectInput();
            } catch (IllegalStateException exception){
                Console.printerror("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return name;
    }

    public String InputPasspordId() throws InCorrectInput{
        String PasspordId;
        while (true) {
            try {
                Console.println("Введите данные паспорта (Должно быть не меньше 7)");
                PasspordId = sc.nextLine().trim();
                if (status) Console.println(PasspordId);
                if (PasspordId.length() < passpordIdLenght) throw new LimitException();
                if (PasspordId.equals("")) throw new MustBeNotEmpty();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Данные некорректно введены");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (LimitException exception) {
                Console.printerror("Некорректная длинна паспорта");
                if (status) throw new InCorrectInput();
            }catch (MustBeNotEmpty exception){
                Console.printerror("Введите паспорт ");
                if (status) throw new InCorrectInput();
            }
            catch (NumberFormatException exception) {
                Console.printerror("Невереный тип данных");
                if (status) throw new InCorrectInput();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return PasspordId;
    }

    public Color1 InputColor1() throws InCorrectInput{
        String strColor1;
        Color1 color1;
        while (true) {
            try {
                Console.println("Список цветов из колекции №1 - " + Color1.nameList());
                Console.println("Введите нужный вам цвет:");
                strColor1 = sc.nextLine().trim();
                if (status) Console.println(strColor1);
                color1 = Color1.valueOf(strColor1.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Цвет не распознан");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (IllegalArgumentException exception) {
                Console.printerror("Такого цвета нет в списке");
                if (status) throw new InCorrectInput();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return color1;
    }

    public Color2 InputColor2() throws InCorrectInput{
        String strColor2;
        Color2 color2;
        while (true) {
            try {
                Console.println("Список цветов из колекции №2 - " + Color2.nameList());
                Console.println("Введите нужный вам цвет:");
                strColor2 = sc.nextLine().trim();
                if (status) Console.println(strColor2);
                color2 = Color2.valueOf(strColor2.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Цвет не распознан");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (IllegalArgumentException exception) {
                Console.printerror("Такого цвета нет в списке");
                if (status) throw new InCorrectInput();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return color2;
    }

    public Country InputCountry() throws InCorrectInput{
        String strCountry;
        Country country;
        while (true) {
            try {
                Console.println("Список стран - " + Country.nameList());
                Console.println("Введите нужную вам страну:");
                strCountry = sc.nextLine().trim();
                if (status) Console.println(strCountry);
                country = Country.valueOf(strCountry.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Страна не распознана");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (IllegalArgumentException exception) {
                Console.printerror("Такой страны нет в списке");
                if (status) throw new InCorrectInput();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return country;
    }

    public Person IputPerson() throws InCorrectInput {
        String name;
        String PasspordId;
        Color1 eyecolor;
        Color2 haircolor;
        Country country;

        name = InputPersonName();
        PasspordId = InputPasspordId();
        eyecolor = InputColor1();
        haircolor = InputColor2();
        country = InputCountry();

        return new Person(name,PasspordId,eyecolor, haircolor, country);
    }

    public boolean askQuestion(String question) throws InCorrectInput{
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                answer = sc.nextLine().trim();
                if (status) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new LimitException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Ответ не распознан!");
                if (status) throw new InCorrectInput();
                System.exit(0);
            } catch (LimitException exception) {
                Console.printerror("Ответ должен быть представлен знаками '+' или '-'!");
                if (status) throw new InCorrectInput();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }

}
