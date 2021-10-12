package ordersparser.validator;

import ordersparser.exception.OrdersException;

import java.io.File;

public class ArgsValidator {

    public void validateArgs(String[] args) {
        for (String path : args) {
            File file = new File(path);
            if (!file.exists()) {
                throw new OrdersException("File: " + path + " not found");
            } else {
                if (!getFileExtension(file)) {
                    throw new OrdersException("Unknown file extension: " + file.getName());
                }
            }
            // передача в приложение прошедших валидацию файлов
        }
    }

    private boolean getFileExtension(File file) {
        String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
        if (fileExtension.equals("csv") || fileExtension.equals("jsonl")) {
            return true;
        }
        return false;
    }
}
