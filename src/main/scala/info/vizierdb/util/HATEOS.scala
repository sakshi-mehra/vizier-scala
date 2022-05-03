/* -- copyright-header:v2 --
 * Copyright (C) 2017-2021 University at Buffalo,
 *                         New York University,
 *                         Illinois Institute of Technology.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -- copyright-header:end -- */
package info.vizierdb.util

import play.api.libs.json._
import java.net.URL

object HATEOAS
{
  type T = JsArray

  def apply(links: (String, URL)*): T =
    JsArray(build(links).map { v => JsObject(v.mapValues{ JsString(_) }) })

  def build(links: Seq[(String, URL)]): Seq[Map[String, String]] = 
    links.filter { _._2 != null }
         .map { case (rel, href) => Map("rel" -> rel, "href" -> href.toString) }
         .toSeq

  def extend(base: JsValue, links: (String, URL)*): T =
    JsArray(base.as[Seq[JsValue]] ++ build(links).map { Json.toJson(_) })

  def merge(a: JsValue, b: JsValue): T =
    JsArray(a.as[Seq[JsValue]] ++ b.as[Seq[JsValue]])

  val LINKS = "links"

  // General
  val SELF = "self"

  // API
  val API_DOC = "api.doc"
  val API_HOME = "api.doc"

  // Branch
  val BRANCH_CREATE = "branch.create"
  val BRANCH_DELETE = "branch.delete"
  val BRANCH_HEAD = "branch.head"
  val BRANCH_UPDATE = "branch.update"

  // Dataset
  val ANNOTATIONS_UPDATE = "annotations.update"
  val ANNOTATIONS_GET = "annotations.get"
  val DATASET_DOWNLOAD = "dataset.download"
  val DATASET_FETCH_ALL = "dataset.fetch"

  val PAGE_FIRST = "page.first"
  val PAGE_LAST = "page.last"
  val PAGE_NEXT = "page.next"
  val PAGE_PREV = "page.prev"

  // Files
  val FILE_DOWNLOAD = "file.download"
  val FILE_UPLOAD = "file.upload"

  // Modules
  val MODULE_DELETE = "module.delete"
  val MODULE_INSERT = "module.insert"
  val MODULE_REPLACE = "module.replace"
  val MODULE_FREEZE = "module.freeze"
  val MODULE_FREEZE_ONE = "module.freeze_one"
  val MODULE_THAW = "module.thaw"
  val MODULE_THAW_ONE = "module.thaw_one"

  // Projects
  val PROJECT_CREATE = "project.create"
  val PROJECT_IMPORT = "project.import"
  val PROJECT_LIST = "project.list"
  val PROJECT_DELETE = "project.delete"
  val PROJECT_UPDATE = "project.update"

  // Workflow
  val WORKFLOW_APPEND = "workflow.append"
  val WORKFLOW_BRANCH = "workflow.branch"
  val WORKFLOW_CANCEL = "workflow.cancel"
  val WORKFLOW_PROJECT = "workflow.project"

  val PYTHONSTUBSERVER = "python.stubserver"
}

