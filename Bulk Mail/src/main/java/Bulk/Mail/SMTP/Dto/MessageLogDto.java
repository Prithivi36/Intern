package Bulk.Mail.SMTP.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageLogDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String message;
}
