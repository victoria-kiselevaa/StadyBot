# Телеграм-бот

Назначение программы – генерация тестовых заданий в ТГ-канал в различных режимах: 

1)обучение 
2) экзамен 
3) самопроверка

Основные функции программы:

- генерация заданного числа тестовых заданий

- проверка ответов пользователя

- статистика ответов пользователя

Системные требования: 

Нефункциональные требования:

1)	логгирование всех видов запросов пользователя;

2)	управление политикой выставления оценки.



## Сценарий использования

**Сценарий 1** - Обучение

Бот генерирует случайное заданий по теме, проверяет ответ пользователя и дает правильный ответ

**Сценирий 2**: - Контроль знаний

Система генерирует N заданий, проверяет ответы пользователя и ставит оценку по установленному правилу

**Сценарий 3** - Самопроверка

Система генерирует 10 заданий, проверяет ответы пользователя и выводит статистику (% успешных ответов)


## Реализация

Использован паттерн Медиатор
тип вопросов короткие ответы

o's buried in Grant's tomb?{=no one =nobody} 

Two plus two equals {=four =4}.  Who's buried in Grant's tomb? {   =no one#excellent answer! =nobody#excellent answer! } 

::Jesus' hometown:: Jesus Christ was from {  =Nazareth#Yes! That's right! =%75%Nazereth#Right, but misspelled.  =%25%Bethlehem#He was born here, but not raised here.  }
