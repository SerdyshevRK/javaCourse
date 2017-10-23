public class LinkedList {
    Container head;
    int length = 0;

    public void add(Object object){                     // add new element to the end of list
        Container container;
        if (head == null){
            head = new Container(object);
        } else {
            container = head;
            while (container.next != null){
                container = container.next;
            }
            container.next = new Container(object);
        }
        length = indexLoader(head);
    }
    /*
    public void add(Object value){
        Container container;
        if(head == null) {
            head = new Container(value);
        } else {
            if (head.value > value){
                Container tmp = new Container(value);
                tmp.next = head;
                head = tmp;
                length = indexLoader(head);
                return;
            }
            container = head;
            while (container.next != null){
                if (container.next.value > value){
                    Container tmp = new Container(value);
                    tmp.next = container.next;
                    container.next = tmp;
                    break;
                }
                container = container.next;
            }
            if (container.next == null){
                container.next = new Container(value);
            }
        }
        length = indexLoader(head);
    }
    */
    public Object get(int index){
        Container container;
        if (index == 0 && head != null){
            return head.value;
        } else {
            if (head == null)
                return -1;
            container = head;
            while (container.next != null){
                if (container.index == index)
                    break;
                container = container.next;
            }
            if (container.index != index)
                return -1;
            return container.value;
        }
    }
    public Object remove(int index){
        Object retVal;
        Container container;
        if (index == 0 && head != null){
            head = head.next;
            length = indexLoader(head);
            return head.value;
        }
        if (head == null)
            return -1;
        container = head;
        while (container.next.next != null){
            if (container.next.index == index)
                break;
            container = container.next;
        }
        if (container.next.index != index)
            return -1;
        retVal = container.next.value;
        container.next = container.next.next;
        length = indexLoader(head);
        return retVal;
    }
    private int indexLoader(Container container){
        int index = 0;
        container.index = index;
        index++;
        while (container.next != null){
            container = container.next;
            container.index = index;
            index++;
        }
        return index;
    }
}
