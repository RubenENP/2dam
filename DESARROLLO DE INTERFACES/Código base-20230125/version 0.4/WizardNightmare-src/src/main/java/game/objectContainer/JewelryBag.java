package main.java.game.objectContainer;

import main.java.game.Domain;
import main.java.game.objectContainer.exceptions.ContainerFullException;
import main.java.game.objectContainer.exceptions.ContainerUnacceptedItemException;
import main.java.game.object.Item;
import main.java.game.object.Necklace;
import main.java.game.object.Ring;

public class JewelryBag extends Container{

    public JewelryBag(int c) { super(Domain.NONE, c); }

    /**
     * Adds just a ring or a necklace
     * @param i the item to be added
     * @throws ContainerFullException the container is full
     * @throws ContainerUnacceptedItemException the container don't accept that item
     */
    public void add(Item i) throws ContainerUnacceptedItemException, ContainerFullException {
        if(isFull())
            throw new ContainerFullException();
        else if(i instanceof Necklace || i instanceof Ring) {
            items.add(i);
        }else
            throw new ContainerUnacceptedItemException();
    }

}
