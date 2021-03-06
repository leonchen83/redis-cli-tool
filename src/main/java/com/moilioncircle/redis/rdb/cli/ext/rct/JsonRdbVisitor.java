/*
 * Copyright 2016-2017 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moilioncircle.redis.rdb.cli.ext.rct;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.moilioncircle.redis.rdb.cli.api.format.escape.Escaper;
import com.moilioncircle.redis.rdb.cli.conf.Configure;
import com.moilioncircle.redis.rdb.cli.ext.AbstractJsonRdbVisitor;
import com.moilioncircle.redis.rdb.cli.glossary.DataType;
import com.moilioncircle.redis.rdb.cli.util.OutputStreams;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.io.RedisInputStream;

/**
 * @author Baoyi Chen
 */
public class JsonRdbVisitor extends AbstractJsonRdbVisitor {
    
    public JsonRdbVisitor(Replicator replicator, Configure configure, File out, List<Long> db, List<String> regexs, List<DataType> types, Escaper escaper) {
        super(replicator, configure, out, db, regexs, types, escaper);
    }

    @Override
    public String applyMagic(RedisInputStream in) throws IOException {
        OutputStreams.write('[', out);
        return super.applyMagic(in);
    }

    @Override
    public long applyEof(RedisInputStream in, int version) throws IOException {
        OutputStreams.write(']', out);
        return super.applyEof(in, version);
    }

    /**
     *
     */
    protected void separator() {
        OutputStreams.write(',', out);
        OutputStreams.write('\n', out);
    }
    
}
