package seventeam.tgbot.model;

import com.pengrad.telegrambot.model.File;

public record Report(File photo, String report) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report1 = (Report) o;
        return photo.equals(report1.photo) && report.equals(report1.report);
    }

}
