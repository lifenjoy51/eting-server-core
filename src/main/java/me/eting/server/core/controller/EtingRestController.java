package me.eting.server.core.controller;

import me.eting.common.domain.EtingLang;
import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.story.ExchangedStory;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;
import me.eting.server.core.dto.DeviceDto;
import me.eting.server.core.dto.ExchangedStoryDto;
import me.eting.server.core.dto.ReplyDto;
import me.eting.server.core.dto.StoryDto;
import me.eting.server.core.service.reply.ReplyService;
import me.eting.server.core.service.story.StoryQueueConsumer;
import me.eting.server.core.service.story.StoryService;
import me.eting.server.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lifenjoy51 on 2015-03-04.
 */
@Controller
@RequestMapping("/api")
public class EtingRestController {

    @Autowired
    UserService userService;
    
    @Autowired
    StoryService storyService;
    
    @Autowired
    StoryQueueConsumer storyQueueConsumer;

    @Autowired
    ReplyService replyService;

    @RequestMapping("/test")
    @ResponseBody
    public int test() {
        storyQueueConsumer.handleStories();
        return 1234;
    }


    

    //POST    /device/uuid&os
    @RequestMapping(value = "/v1/device", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> deviceRegistration(
            @ModelAttribute DeviceDto deviceDto,
            @RequestParam(value = "lang", required = false, defaultValue = "basic") String lang
    ) {
        try {
            Device device = new Device(deviceDto);
            Incognito incognito = userService.register(device, EtingLang.valueOf(lang));
            String incognitoId = String.valueOf(incognito.getId());
            return new ResponseEntity<String>(incognitoId, HttpStatus.OK);  //id를 리턴.
        }catch (NumberFormatException nfe){
            //디바이스 시간이 제대로 들어오지 않을 경우. 에러처리 필요함!
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException iae){
            //이넘 타입이 제대로 맞지 않을 때.
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
    
    
    //POST    /story?deviceId&storyId&storyContent&StoryDt
    @RequestMapping(value = "/v1/story", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> newStory(
            @ModelAttribute StoryDto storyDto
    ) {
        try {
            //incognito 정보를 불러온다.
            Incognito incognito = userService.get(storyDto.getIncognitoId());
            Story story = new Story(storyDto, incognito);

            Story savedStory = storyService.save(story);
            String storyId = String.valueOf(savedStory.getId());
            return new ResponseEntity<String>(storyId, HttpStatus.OK);  //id를 리턴.
            
        }catch (NumberFormatException nfe){
            //디바이스 시간이 제대로 들어오지 않을 경우. 에러처리 필요함!
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException iae){
            //이넘 타입이 제대로 맞지 않을 때.
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
    
    //GET /story/random?deviceId
    //GET /exchange?incognitoId
    @RequestMapping(value = "/v1/exchange/story", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ExchangedStoryDto> getRandomStory(
            @RequestParam(value = "incognitoId", required = true) int incognitoId
    ) {
        try {
            //incognito 정보를 불러온다.
            Incognito incognito = userService.get(incognitoId);
            
            
            ExchangedStory exchangedStory = storyService.exchange(incognito);
            System.out.println(exchangedStory);
            ExchangedStoryDto dto = new ExchangedStoryDto(exchangedStory);
            return new ResponseEntity<ExchangedStoryDto>(dto, HttpStatus.OK);  //id를 리턴.

        }catch (NumberFormatException nfe){
            //디바이스 시간이 제대로 들어오지 않을 경우. 에러처리 필요함!
            return new ResponseEntity<ExchangedStoryDto>(HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException iae){
            //이넘 타입이 제대로 맞지 않을 때.
            return new ResponseEntity<ExchangedStoryDto>(HttpStatus.BAD_REQUEST);
        }
    }

    //POST    /exchange/{exchangeId}/reply?exchangedId,
    @RequestMapping(value = "/v1/exchange/{exchangeId}/reply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveReply(
            @ModelAttribute ReplyDto replyDto,
            @PathVariable("exchangeId") String exchangeIdString
    ) {
       try {
            //exhanged story.
           long exchangeId = Long.parseLong(exchangeIdString);
           ExchangedStory exchangedStory = storyService.getExchangedStory(exchangeId);

            Reply reply = new Reply(exchangedStory, replyDto);
           System.out.println(reply);
            Reply savedReply = replyService.save(reply);
            String replyId = String.valueOf(savedReply.getId());
            return new ResponseEntity<String>(replyId, HttpStatus.OK);  //id를 리턴.

        }catch (NumberFormatException nfe){
            //디바이스 시간이 제대로 들어오지 않을 경우. 에러처리 필요함!
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException iae){
            //이넘 타입이 제대로 맞지 않을 때.
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    
    
    


    /*
     

    POST    /exchange/{exchangeId}/report
    POST    /exchange/{exchangeId}/pass
    
    POST    /ad/{adId}/reply?deviceId&replyContent
    POST    /adminMsg/{adminMsgId}/reply?deviceId&replyContent
    
    PUT /device/{deviceId}/gender
    PUT /device/{deviceId}/age
    
    POST    /user?deviceId
    GET /user/{userId}/sync
    
    POST    /story/{storyId}/favorite
    DEL /story/{storyId}/favorite
    
    DEL /story/{storyId}
    DEL /exchange/{exchangeId}/reply
    PUT /exchange/{exchangeId}/report
    
    POST    /reply/{replyId}/favorite
    DEL /reply/{replyId}/favorite
    
    DEL /reply/{replyId}
    
    
    
    GET /story/reply?deviceId&storyIdList
    GET /story/{storyId}?deviceId
    
    
    PUT /device/{deviceId}/pushKey
    
    


     * */

}
