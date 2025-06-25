package com.wfh.example.backend.endpoints.rest;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @PostMapping(
            path="/question",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SendQuestionStatus> sendQuestion(
            @RequestBody SendQuestionBody body) {
        System.out.println("=============");
        System.out.println(body.getQuestion());
        System.out.println(body.isAnonymous());

        var response = new SendQuestionStatus(
                "Your question has been submitted",
                true
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class SendQuestionBody {
        @Schema(description = "text of a question")
        private String question;

        @JsonProperty("isAnonymous")
        @Schema(description = "marks if question should be posted anonymously")
        private boolean isAnonymous;

        @JsonGetter("isAnonymous")
        public boolean isAnonymous() {
            return isAnonymous;
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class SendQuestionStatus {
        @Schema(description = "long status for UI")
        private String longDescription;

        @JsonProperty("isApproved")
        @Schema(description = "Is question approved by moderators")
        private boolean isApproved;

        @JsonGetter("isApproved")
        public boolean isApproved() {
            return isApproved;
        }
    }
}
