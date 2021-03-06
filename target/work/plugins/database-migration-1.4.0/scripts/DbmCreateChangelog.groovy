/* Copyright 2010-2013 SpringSource.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */

includeTargets << new File("$databaseMigrationPluginDir/scripts/_DatabaseMigrationCommon.groovy")

target(dbmCreateChangelog: 'Creates an empty changelog file') {
    depends dbmInit

    String name = argsList[0] ?: MigrationUtils.getChangelogFileName(dsName)
    if (!name.toLowerCase().endsWith('.groovy')) {
        name += '.groovy'
    }

    try {
        def file = new File(MigrationUtils.getChangelogLocation(dsName) + '/' + name)
        file.parentFile?.mkdirs()

        if (!okToWrite(file.path)) return

        String user = (System.getProperty('user.name') ?: '').trim()
        String author = user ? "$user (generated)" : 'diff-generated'

        ant.copy(file: "$databaseMigrationPluginDir/src/resources/changelog.template",
                tofile: file.path, verbose: true, overwrite: true) {
            filterset {
                filter token: 'author', value: author
                filter token: 'id', value: file.name - '.groovy'
            }
        }
    }
    catch (e) {
        ScriptUtils.printStackTrace e
        exit 1
    }
}

setDefaultTarget dbmCreateChangelog
