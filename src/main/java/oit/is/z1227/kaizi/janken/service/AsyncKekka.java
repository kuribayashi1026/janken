package oit.is.z1227.kaizi.janken.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1227.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper matchMapper;

  @Async
  public void asyncGetIsActive(SseEmitter emitter, int user1, int user2) {
    try {
      while (true) {
        TimeUnit.MILLISECONDS.sleep(500);
        var matches = this.matchMapper.selectMatches(user1, user2);
        for (var match : matches) {
          if (match.isIs_active()) {
            emitter.send(match);
          }
        }
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowFruitsList complete");
  }
}
