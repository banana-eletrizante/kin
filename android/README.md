# Instagram Clean — Android beta

App nativo em Kotlin com interface de configuração e serviço de acessibilidade experimental.

## Compilar
Abra a pasta `android/` no Android Studio (JDK 17, Android SDK 35). Aguarde a sincronização e use **Build > Build APK(s)**. O APK de debug fica em `app/build/outputs/apk/debug/app-debug.apk`.

## Ativar
Instale, abra o app, toque em “Abrir configurações de acessibilidade” e habilite “Instagram Clean - bloqueador”. Depois abra o Instagram.

## Limitações importantes
O serviço depende dos rótulos que o Instagram expõe na árvore de acessibilidade. Ele tenta voltar uma tela quando detecta um clique em um rótulo conhecido de Reels ou Explorar. Isso não remove visualmente as abas e não garante interceptar todos os caminhos. É necessário testar no Instagram e ajustar os seletores conforme a versão instalada.

O app não é afiliado à Meta. O serviço não transmite nem armazena o conteúdo da tela.
