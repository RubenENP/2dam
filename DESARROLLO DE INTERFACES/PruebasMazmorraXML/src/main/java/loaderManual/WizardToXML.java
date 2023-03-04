package loaderManual;

import game.Domain;
import game.character.Character;
import game.character.Wizard;
import game.objectContainer.CrystalCarrier;
import game.objectContainer.JewelryBag;
import game.objectContainer.Wearables;

import java.io.StringWriter;
import java.lang.reflect.Field;

public class WizardToXML {
    public static void main(String[] args) {
        Wizard w = new Wizard("Mola", 1, 1, 1, 1, new Wearables(1, 1, 1), new CrystalCarrier(2),
                new JewelryBag(2));

        save(w);
    }

    static StringWriter writer = new StringWriter();

    static void save(Wizard wizard) {

        try {
            writer.write("<wizard>\n");

            Field[] characterFields = wizard.getClass().getSuperclass().getDeclaredFields();


//            for (Field field : characterFields) {
//                field.setAccessible(true);
//
//                if (!field.getType().getSimpleName().equals("String") &&
//                        !field.getType().getSimpleName().equals("Domain")){
//                    Field[] f = field.get(wizard).getClass().getDeclaredFields();
//
//                    if (f.length == 0){
//                        writer.write("    <" + field.getName() + ">" + field.get(wizard) + "</" + field.getName() + ">\n");
//                    }else {
//                        for (Field i : f){
//                            if (i.getType().getSimpleName().equals("Value")){
//                                writer.write("    <" + i.getName() + ">" + i.get(wizard) + "</" + i.getName() + ">\n");
//                            }
//
////                            writer.write("    <" + i.getName() + ">" + i.get(wizard) + "</" + i.getName() + ">\n");
//                        }
//                    }
//                } else {
//                    writer.write("    <" + field.getName() + ">" + field.get(wizard) + "</" + field.getName() + ">\n");
//                }
//            }

            Field[] wizardFields = wizard.getClass().getDeclaredFields();

            for (Field field : wizardFields) {
                field.setAccessible(true);
//                if (field.getType().getSimpleName().equals("Value")){
//                    Field[] valueClass =wizard.getEnergy();
//
//                    for (Field i : valueClass){
//                        writer.write("    <" + i.getName() + ">" + i.get(wizard.getEnergy()) + "</" + i.getName() + ">\n");
//                    }
//                }else {
//                    writer.write("    <" + field.getName() + ">" + field.get(wizard) + "</" + field.getName() + ">\n");
//                }

                switch (field.getType().getSimpleName()){
                    case "Value":
                        writer.write("    <" + field.getName() + ">" + wizard.getEnergy() + "</" + field.getName() + ">\n");
                        break;
                    case "Wearables":
                        Field[] wereables = wizard.getWearables().getClass().getDeclaredFields();
                        writer.write("    <" + field.getName() + ">\n");
                        for (Field f : wereables){
                            writer.write("      <" + f.getName() + ">" + wizard.getWearables() + "</" + f.getName() + ">\n");
                        }
                        writer.write("    </" + field.getName() + ">\n");
                        break;
                    default:
                        writer.write("    <" + field.getName() + ">" + field.get(wizard) + "</" + field.getName() + ">\n");
                        break;
                }
            }

            writer.write("</wizard>");

            System.out.println(writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
