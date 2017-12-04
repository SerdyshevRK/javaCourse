package com.collections;

import java.util.*;

/**
 * Created by xmitya on 20.10.16.
 */
public class MessageProcessor {
    private static final String PROCESS_CMD = "process";
    private static final String UNDO_CMD = "undo";
    private static final String REDO_CMD = "redo";
    private static final String EXIT_CMD = "exit";
    private static final String HELP_CMD = "help";

    private static final String SEPARATOR_1 = " ";
    private static final String SEPARATOR_2 = ",";

    private Stack<List<Message>> undoStack = new Stack<>(10);
    private Stack<List<Message>> redoStack = new Stack<>(10);
    private List<Message> messagesToProcess = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MessageProcessor processor = new MessageProcessor();

        System.out.println("Welcome!");
        printUsage();

        while (true) {
            String line = scanner.nextLine().trim();

            if (EXIT_CMD.equals(line))
                return;

            else if (HELP_CMD.equals(line))
                printUsage();

            else if(PROCESS_CMD.equals(line))
                processor.processMessages();

            else if (line.startsWith(UNDO_CMD))
                processor.undo(steps(line));

            else if (line.startsWith(REDO_CMD))
                processor.redo(steps(line));

            else if (!line.isEmpty()) {
                String[] messageSplit = line.split(SEPARATOR_2);
                List<Message> list = new ArrayList<>();

                for (String mes : messageSplit) {
                    String[] argSplit = mes.trim().split(SEPARATOR_1);
                    Message message = new Message(
                            MessagePriority.fromOrdinal(Integer.parseInt(argSplit[0])),
                            Integer.parseInt(argSplit[1])
                    );
                    list.add(message);
                    processor.newMessage(message);
                }
                processor.undoStack.push(list);
            }
        }
    }

    private static void printUsage() {
        System.out.println("Usage: ");
        System.out.println("Enter messages separated by comma or enter. Message priority and code are separated by space. F.e.:");
        System.out.println("0 34, 3 45, 2 1");
        System.out.println("Priority may be from 0 to " + (MessagePriority.values().length - 1));
        System.out.println();
        System.out.println("Type commands:");
        System.out.println("'process' to start processing messages.");
        System.out.println("'undo <steps>' undo last message processing steps, or one step if nothing set.");
        System.out.println("'redo <steps>' redo last message processing steps, or one step if nothing set.");
        System.out.println("'exit' to exit.");
    }

    private static int steps(String line) {
        String[] split = line.split(SEPARATOR_1);

        return split.length > 1 ? Integer.parseInt(split[1].trim()) : 1;
    }

    private void newMessage(Message message) {
        System.out.println("Added message for processing: " + message);
        messagesToProcess.add(message);
    }

    private void processMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("Processing messages:");
        for (Message message : messagesToProcess) {
            sb.append(" " + message);
        }
        System.out.println(sb.toString());
    }

    private void process(Message message) {
        System.out.println("Message processed: " + message);
    }

    private void cancel(Message message) {
        System.out.println("Operation canceled for: " + message);
    }

    private void undo(int steps) {
        if (steps == 0) {
            execute(true);
            processMessages();
            return;
        }
        for (int i = 0; i < steps; i++) {
            execute(true);
        }
        processMessages();
    }

    private void redo(int steps) {
        if (steps == 0){
            execute(false);
            processMessages();
            return;
        }
        for (int i = 0; i < steps; i++) {
            execute(false);
        }
        processMessages();
    }
    // remove messages from one stack (undo/redo) and add to another.
    private void execute(boolean setOperation){                 // setOperation:
        if (setOperation) {                                     // true = undo,
            redoStack.push(undoStack.peek());                   // false = redo.
            messagesToProcess.removeAll(undoStack.pop());
            return;
        }
        undoStack.push(redoStack.peek());
        messagesToProcess.addAll(redoStack.pop());
    }
}
