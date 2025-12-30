package com.example.shareviewnotificationservice.infrastructure.input.api.controllers.notification;

import com.example.shareviewnotificationservice.controllers.NotificationController;
import com.example.shareviewnotificationservice.datasources.FeedbackDataSource;
import com.example.shareviewnotificationservice.datasources.NotificationDataSource;
import dtos.requests.CreateBadFeedbackNotificationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/notificacoes")
@Tag(name = "Notification API V1", description = "Versão 1 do controlador referente a notificações")
@Profile("api")
public class NotificationApiV1 {

    private final NotificationController notificationController;

    public NotificationApiV1(NotificationDataSource notificationDataSource, FeedbackDataSource feedbackDataSource) {
        this.notificationController = new NotificationController(notificationDataSource, feedbackDataSource);
    }

    @Operation(summary = "Envia notificação referente a feedback negativo recebido",
            description = "Requer autenticação",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Notificação enviado com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos da notificação a ser enviada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<Void> sendBadFeedbackNotification(@RequestBody @Valid CreateBadFeedbackNotificationRequest createBadFeedbackNotificationRequest) {
        log.info("Sending notification from bad feedback received from student {} of course: {}", createBadFeedbackNotificationRequest.student(), createBadFeedbackNotificationRequest.course());
        notificationController.sendBadFeedbackNotificationUseCase(createBadFeedbackNotificationRequest);
        log.info("Sent notification from bad feedback received from student {} of course: {}", createBadFeedbackNotificationRequest.student(), createBadFeedbackNotificationRequest.course());

        return ResponseEntity
                .status(HttpStatus.OK).build();
    }

}
