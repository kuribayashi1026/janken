package oit.is.z1227.kaizi.janken.service;

import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import oit.is.z1227.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {
  int i = 0;

  @Autowired
  MatchMapper matchMapper;
  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Async
  public void GetActive(SseEmitter emitter) {
    try {
      while (true) {
        TimeUnit.MILLISECONDS.sleep(500);
        i = matchMapper.getactive();
        if (i == 0) {
          continue;
        }
        emitter.send(i);
        i = 0;
      }
    } catch (Exception e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncGetIsActive complete");
  }
}
