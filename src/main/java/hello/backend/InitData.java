package hello.backend;

import hello.backend.domain.Member;
import hello.backend.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitData {
    private final InitService initService;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() throws IOException {
        log.info("데이터 초기화 시작");
        initService.init();
    }


    @Component
    @RequiredArgsConstructor
    @Transactional
    public static class InitService {
        private final MemberRepository memberRepository;

        public void init() throws IOException {
            initMember();
        }

        private void initMember() {
            initMember("kim");
            initMember("lee");
        }

        private void initMember(String name) {
            Member member = Member.builder()
                    .name(name).build();
            memberRepository.save(member);
        }
    }
}
