package com.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by xmitya on 17.10.16.
 */
public class Tasks1 {

    public static void main(String[] args) {
        MessageGenerator generator = new MessageGenerator();

        List<Message> messages = generator.generate(100);

        countEachPriority(messages);
        countCountEachCode(messages);
        countUniqueMessages(messages);

        System.out.println("Genuine messages in natural order: \n" + genuineMessagesInOriginalOrder(messages));

        removeEach(generator.generate(100), MessagePriority.LOW);
        removeOther(generator.generate(100), MessagePriority.URGENT);
    }

    private static void countEachPriority(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого приоритета.
        // Ответ необходимо вывести в консоль.

        int[] countPriority = new int[MessagePriority.values().length];
        for (Message message : messages) {
            countPriority[message.getPriority().ordinal()]++;
        }
        System.out.println("Сообщений с приоритетом:");
        for (int i = 0; i < countPriority.length; i++) {
            System.out.println("\t" + MessagePriority.fromOrdinal(i) + " - " + countPriority[i]);
        }
    }

    private static void countCountEachCode(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого кода сообщения.
        // Ответ необходимо вывести в консоль.

        int[] countCode = new int[10];
        for (Message message : messages) {
            countCode[message.getCode()]++;
        }
        System.out.println("Сообщений с кодом:");
        for (int i = 0; i < countCode.length; i++) {
            System.out.println("\t" + i + " - " + countCode[i]);
        }
    }

    private static void countUniqueMessages(List<Message> messages) {
        // Сосчитайте количество уникальных сообщений.
        // Ответ необходимо вывести в консоль.

        int count = 0;
        for (Message message : messages) {
            if (messages.indexOf(message) == messages.lastIndexOf(message))
                count++;
        }
        System.out.println("Уникальных сообщений: " + count);
    }

    private static List<Message> genuineMessagesInOriginalOrder(List<Message> messages) {
        // Здесь необходимо вернуть только неповторяющиеся сообщения и в том порядке, в котором
        // они встречаются в первоначальном списке. Например, мы на входе имеем такие сообщения:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}],
        // то на выходе должны получить:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}].
        // Т.е. остались только уникальные значения, и порядок их поступления сохранен.

        List<Message> retList = new ArrayList<>();
        for (Message message : messages) {
            if (retList.contains(message))
                continue;
            retList.add(message);
        }
        countDuplicates(messages);
        countDuplicates(retList);
        return retList;
    }
    // utility for testing
    private static void countDuplicates(List<Message> messages){
        int count = 0;
        for (Message message : messages) {
            if (messages.indexOf(message) != messages.lastIndexOf(message))
                count++;
        }
        System.out.println("Количество повторов: " + count);
    }

    private static void removeEach(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции каждое сообщение с заданным приоритетом.
        System.out.printf("Before remove each: %s, %s\n", priority, messages);

        messages.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return message.getPriority() == priority;
            }
        });

        System.out.printf("After remove each: %s, %s\n", priority, messages);
    }

    private static void removeOther(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет.
        System.out.printf("Before remove other: %s, %s\n", priority, messages);

        messages.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return message.getPriority() != priority;
            }
        });
        System.out.printf("After remove other: %s, %s\n", priority, messages);
    }
}
