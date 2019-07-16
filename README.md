# Эмуляторы загрузки документов
## Для запуска любого из эмуляторов необходимо перейти в папку эмулятора в репозитории
###### Далее необходимо выполнить в кончоли команду
```
npm install
```
## далее необходимо отредактировать config.js
###### tmpDocument, tmpDocumentWithSignature(tmpSignature) путь к двум временным файлам, которые при загрузке будут заполняться js и вычитываться jmeter
###### jmeterCmd строка запуска jmeter, в которой необходимо указать путь до исполняемого файла jmeter.bat, первым аргументом которой будет являться файл Договор.jmx находящийся в папке \_релиз N каждого из эмуляторов
###### во втором эмуляторе параметр в config.js filesFolder - путь до папки в которой будут храниться архивы, документы, метафайлы для отоббражения таблицы загруженных файлов
## для запуска каждого из эмуляторов необходимо в консоли из папки эмулятора выполнить команду
```
node index.js
```
## для корректной работы второго эмулятора необходимо запустить jmx файл из папки \_релиз N Договор_modified.jmx, у которого в параметрах в filesFolder записать путь до папки, указанной в том же параметре в config.js
