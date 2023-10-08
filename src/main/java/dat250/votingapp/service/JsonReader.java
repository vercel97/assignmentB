package dat250.votingapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import dat250.votingapp.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;



public class JsonReader {
    ObjectMapper objectMapper = new ObjectMapper();
   public List<AppUser> readUserData(String filePath) throws IOException {
       // Read users.json
       List<AppUser> users = objectMapper.readValue(new File(filePath), new TypeReference<List<AppUser>>() {
       });
       for (AppUser user : users) {
           System.out.println(user.getUsername());
       }

       return users;
   }

   public List<Poll> readPollData(String filePath) throws IOException {
       // Read polls.json
       List<Poll> polls = objectMapper.readValue(new File(filePath), new TypeReference<List<Poll>>() {
       });
       for (Poll poll : polls) {
           System.out.println(poll.getPollTitle());
       }

       return polls;
   }

    public List<Question> readVotingData(String filePath) throws IOException {
        // Read polls.json
        List<Question> votes = objectMapper.readValue(new File(filePath), new TypeReference<List<Question>>() {
        });
        for (Question vote : votes) {
            System.out.println(vote.getQuestionText());
        }

        return votes;
    }

}
