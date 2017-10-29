package com.lazyAccumulator;
import com.accumulator.Operation;
import com.lists.*;

public class LazyAccumulator {
    double value;
    List list;
    Stack stack;

    public LazyAccumulator(double value, List list, Stack stack){
        this.list = list;
        this.value = value;
        this.stack = stack;
    }

    public void add(double value, Operation operation, boolean which){      // which: 0 - list, 1 - stack
        if (!which) {
            list.add(new Data(value, operation));
            return;
        }
        stack.push(new Data(value, operation));
    }
    public double calculate(boolean which){
        Data data;
        if (!which) {
            for (Object obj : list) {
                data = (Data) obj;
                this.value = data.operation.doOperation(this.value, data.value);
            }
        } else {
            Stack tmp = new BidirectionalList();
            while ((data = (Data) stack.pop()) != null) {
                tmp.push(data);
            }
            while ((data = (Data) tmp.pop()) != null) {
                this.value = data.operation.doOperation(this.value, data.value);
            }
        }
        return this.value;
    }
}
